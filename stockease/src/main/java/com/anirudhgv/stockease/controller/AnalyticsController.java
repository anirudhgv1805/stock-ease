// package com.anirudhgv.stockease.controller;

// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.anirudhgv.stockease.model.Inventory;
// import com.anirudhgv.stockease.model.Order;
// import com.anirudhgv.stockease.model.OrderItem;
// import com.anirudhgv.stockease.model.ProductionBatch;
// import com.anirudhgv.stockease.repository.InventoryRepository;
// import com.anirudhgv.stockease.repository.OrderItemRepository;
// import com.anirudhgv.stockease.repository.OrderRepository;
// import com.anirudhgv.stockease.repository.ProductionBatchRepository;

// @RestController
// @RequestMapping("/api/analytics")
// public class AnalyticsController {

//     @Autowired
//     private OrderRepository orderRepository;

//     @Autowired
//     private OrderItemRepository orderItemRepository;

//     @Autowired
//     private ProductionBatchRepository productionBatchRepository;

//     @Autowired
//     private InventoryRepository inventoryRepository;

//     @GetMapping("/sales-by-month")
//     public Map<String, Double> getSalesByMonth() {
//         List<Order> orders = orderRepository.findAll();
//         return orders.stream()
//                 .collect(Collectors.groupingBy(
//                         order -> order.getOrderDate().getMonth().toString() + "-" + order.getOrderDate().getYear(),
//                         Collectors.summingDouble(order -> order.getTotalAmount().doubleValue())));
//     }

//     @GetMapping("/sales-by-product")
//     public Map<String, Integer> getSalesByProduct() {
//         List<OrderItem> items = orderItemRepository.findAll();
//         return items.stream()
//                 .collect(Collectors.groupingBy(
//                         item -> item.getProduct().getName(),
//                         Collectors.summingInt(OrderItem::getQuantity)));
//     }

//     @GetMapping("/production-summary")
//     public Map<String, Integer> getProductionSummary() {
//         List<ProductionBatch> batches = productionBatchRepository.findAll();
//         return batches.stream()
//                 .collect(Collectors.groupingBy(
//                         batch -> batch.getProduct().getName(),
//                         Collectors.summingInt(batch -> batch.getQuantityProduced())));
//     }

//     @GetMapping("/low-stock")
//     public List<Inventory> getLowStockProducts(@RequestParam(defaultValue = "10") int threshold) {
//         return inventoryRepository.findAll().stream()
//                 .filter(inv -> inv.getQuantity() <= threshold)
//                 .collect(Collectors.toList());
//     }

//     @GetMapping("/top-clients")
//     public Map<String, Double> getTopClients() {
//         List<Order> orders = orderRepository.findAll();

//         return orders.stream()
//                 .collect(Collectors.groupingBy(
//                         o -> o.getUser().getName(),
//                         Collectors.summingDouble(o -> o.getTotalAmount().doubleValue())));
//     }

// }
