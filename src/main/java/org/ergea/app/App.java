package org.ergea.app;

import org.ergea.model.History;
import org.ergea.model.MenuItem;
import org.ergea.model.OrderItem;
import org.ergea.repositoryimpl.HistoryRepositoryImpl;
import org.ergea.repositoryimpl.MenuRepositoryImpl;
import org.ergea.repositoryimpl.OrderRepositoryImpl;
import org.ergea.service.HistoryService;
import org.ergea.service.MenuService;
import org.ergea.service.OrderService;

import java.io.File;
import java.util.*;
import java.util.stream.IntStream;

import static org.ergea.utils.Utils.INPUT;
import static org.ergea.utils.Utils.formatToRupiah;

public class App {
    private static final OrderService orderService;
    private static final MenuService menuService;
    private static final HistoryService historyService;
    private static final List<OrderItem> orderItems = new ArrayList<>();

    static {
        orderService = new OrderService(new OrderRepositoryImpl(orderItems));
        menuService = new MenuService(new MenuRepositoryImpl());
        historyService = new HistoryService(new HistoryRepositoryImpl());
    }

    public void run() {
        boolean isOrdering = false;
        while (!isOrdering) {
            try {
                orderMenu();
                isOrdering = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Only numbers are allowed. Restarting the program!");
                System.out.println("Try again!");
                INPUT.nextLine();
            }
        }
        INPUT.close();
    }

    private void orderMenu() throws InputMismatchException {
        System.out.println("--------------------------");
        System.out.println("Welcome to Binarfud");
        System.out.println("--------------------------");
        List<MenuItem> menuItems = menuService
                .getMenuRepository()
                .getMenuItems()
                .orElseGet(Collections::emptyList);
        IntStream.range(0, menuItems.size())
                .mapToObj(i -> (i + 1) + ".\t" + menuItems.get(i).getName() + " | " + formatToRupiah(menuItems.get(i).getPrice()))
                .forEach(System.out::println);
        System.out.println("99. Order and Pay");
        System.out.println("0. Exit");
        System.out.print("=> ");

        int choice = INPUT.nextInt();

        if (choice == 0) return;

        if (choice >= 1 && choice <= menuItems.size()) orderDetailFood(menuItems.get(choice - 1));

        else if (choice == 99) pay();

        else System.out.println("Please choose a valid option!");

        orderMenu();
    }

    private void orderDetailFood(MenuItem menuItem) {
        System.out.println("----------------------------");
        System.out.println("How many would you like to order?");
        System.out.println("----------------------------");
        System.out.println("\n" + menuItem.getName() + "\t| " + formatToRupiah(menuItem.getPrice()));
        System.out.println("(Input 0 to go back)\n");
        System.out.print("qty -> ");
        int quantity = INPUT.nextInt();

        if (quantity == 0) return;

        if (orderItems
                .stream()
                .anyMatch(orderItem -> orderItem.getMenuItem().getName().contains(menuItem.getName()))
        ) {
            orderItems.stream()
                    .filter(orderItem ->
                            Objects.equals(orderItem.getMenuItem().getName(), menuItem.getName()))
                    .forEach(orderItem ->
                            orderItem.setQuantity(orderItem.getQuantity() + quantity));
        } else {
            orderItems.add(new OrderItem(menuItem, quantity));
        }
    }

    private void pay() {
        if (!orderItems.isEmpty()) {
            System.out.println("--------------------------------------------");
            System.out.println("Confirmation & Payment");
            System.out.println("--------------------------------------------");
            int count = 1;
            for (OrderItem orderItem : orderItems) {
                System.out.println(count + ". " + orderItem.getMenuItem().getName() + "\t" + orderItem.getQuantity() + "\t" + formatToRupiah(orderItem.getMenuItem().getPrice() * orderItem.getQuantity()));
                count++;
            }
            System.out.println("-------------------------------------------+");
            System.out.println("Total Quantity: " + orderService.getOrderRepository().getTotalQuantity() + " Total Price: " + formatToRupiah(orderService.getOrderRepository().getTotalPrice().orElseThrow()));
            System.out.println("--------------------------------------------\n");
            System.out.println("1. Confirm & Pay");
            System.out.println("2. Go back to main menu");
            System.out.println("0. Exit");
            System.out.print("=> ");
            int choice = INPUT.nextInt();
            if (choice == 2) return;
            if (choice == 1) {
                String folderName = "Order History";
                File folder = new File(folderName);
                if (!folder.exists()) folder.mkdir();
                historyService.getHistoryRepository().printOrder(new History(orderItems), folderName);
                orderItems.clear();
                repeatOrder();
            } else if (choice == 0) {
                System.exit(0);
            } else {
                System.out.println("Invalid choice, please try again!");
                pay();
            }
        } else {
            System.out.println("You haven't ordered anything yet. Please order 1 menu!\n");
        }
    }

    private void repeatOrder() {
        System.out.println("--------------------------------------------");
        System.out.println("Want to repeat order? ");
        System.out.println("--------------------------------------------");
        System.out.println("(Y) Continue");
        System.out.println("(N) Exit");
        System.out.print("=> ");
        String repeatOrder = INPUT.next();
        if (!repeatOrder.equalsIgnoreCase("y")) {
            if (repeatOrder.equalsIgnoreCase("n")) {
                System.exit(0);
            } else {
                System.out.println("Input not available! Out program!");
                System.exit(0);
            }
        }
    }
}
