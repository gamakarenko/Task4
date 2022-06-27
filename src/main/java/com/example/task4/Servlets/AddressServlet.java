package com.example.task4.Servlets;

import com.example.task4.DAO.AddressDAO;
import com.example.task4.Entities.Address;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/address")
public class AddressServlet extends HttpServlet {
    private AddressDAO addressDAO;

    public void init() {
        addressDAO = new AddressDAO();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(req, resp);
                break;
            case "/insert":
                insertAddress(req, resp);
                break;
            case "/delete":
                deleteAddress(req, resp);
                break;
            case "/edit":
                showEditForm(req, resp);
                break;
            case "/update":
                updateAddress(req, resp);
                break;
            default:
                listAddress(req, resp);
                break;
        }
    }

    private void listAddress(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Address> listAddress = addressDAO.getAllAddress();
        req.setAttribute("listAddress", listAddress);
        RequestDispatcher dispatcher = req.getRequestDispatcher("address-list.jsp");
        dispatcher.forward(req, resp);
    }

    private void updateAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("address_id"));
        String street = req.getParameter("street");
        int house = Integer.parseInt(req.getParameter("house"));

        Address address = new Address(id, street, house);
        addressDAO.updateAddress(address);
        resp.sendRedirect("list");
    }


    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("address_id"));
        Address existingAddress = addressDAO.getAddress(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("address-form.jsp");
        req.setAttribute("user", existingAddress);
        dispatcher.forward(req, resp);
    }

    private void deleteAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("address_id"));
        addressDAO.deleteAddress(id);
        resp.sendRedirect("list");
    }

    private void insertAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String street = req.getParameter("street");
        int house = Integer.parseInt(req.getParameter("house"));
        Address address = new Address(street, house);
        addressDAO.saveAddress(address);
        resp.sendRedirect("list");

    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/user-form.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

