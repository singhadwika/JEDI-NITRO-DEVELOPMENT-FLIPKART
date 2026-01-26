package com.flipfit.dao;

import com.flipfit.bean.GymCustomer;
import java.util.ArrayList;
import java.util.List;

public class GymCustomerDAOImpl implements GymCustomerDAO {

    private static List<GymCustomer> customerList = new ArrayList<>();
    private static int customerCounter = 1;

    @Override
    public boolean addCustomer(GymCustomer customer) {

        customer.setId(customerCounter++);
        customerList.add(customer);

        return true;
    }

    @Override
    public GymCustomer getCustomerById(int customerId) {

        for (GymCustomer customer : customerList) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public List<GymCustomer> getAllCustomers() {
        return customerList;
    }
}
