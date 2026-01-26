package com.flipfit.dao;

import com.flipfit.bean.GymCustomer;
import java.util.List;

public interface GymCustomerDAO {

    boolean addCustomer(GymCustomer customer);

    GymCustomer getCustomerById(int customerId);

    List<GymCustomer> getAllCustomers();
}
