package cz.uhk.ppro.projekt.service;

import cz.uhk.ppro.projekt.entity.Order;
import cz.uhk.ppro.projekt.entity.OrderRow;
import cz.uhk.ppro.projekt.entity.Product;
import cz.uhk.ppro.projekt.model.OrderSummary;
import cz.uhk.ppro.projekt.repository.OrderRepository;
import cz.uhk.ppro.projekt.repository.OrderRowRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderRowRepository orderRowRepository;

    public OrderService(OrderRepository orderRepository, OrderRowRepository orderRowRepository) {
        this.orderRepository = orderRepository;
        this.orderRowRepository = orderRowRepository;
    }

    @Transactional
    public Order save(OrderSummary summary, List<Product> productsToBeOrdered) {
        OrderSummary.DeliveryDetails dd = summary.getDeliveryDetails();
        Order order = new Order(dd.getCity(),dd.getEmail(), dd.getName(), dd.getHouseNumber(), dd.getSurname(),
                dd.getStreet(), dd.getPhoneNumber(), dd.getZipCode(), null,dd.getState(), dd.getPhonePrefix(),
                (byte) 0);
        Order savedOrder = orderRepository.save(order);

        for (int i = 0; i < productsToBeOrdered.size(); i++) {
            OrderRow orderRow = new OrderRow(summary.getCart().get(i).getAmmount(),productsToBeOrdered.get(i).getPrice(),
                    productsToBeOrdered.get(i).getProductId(),savedOrder.getOrderId());
            orderRowRepository.save(orderRow);
        }
        return savedOrder;
    }
}
