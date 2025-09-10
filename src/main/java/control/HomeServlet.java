package control;

import java.io.IOException;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CatagoryServiceImpl;
import service.CategoryService;

@WebServlet({"/user/home", "/manager/home", "/admin/home"})
public class HomeServlet extends HttpServlet{
	private CategoryService categoryService = new CatagoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        String uri = req.getRequestURI();

        if (uri.endsWith("/user/home")) {
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("/view/user/home.jsp").forward(req, resp);
        } else if (uri.endsWith("/manager/home")) {
            req.setAttribute("categories", categoryService.findByUserId(user.getUserId()));
            req.getRequestDispatcher("/view/manager/home.jsp").forward(req, resp);
        } else if (uri.endsWith("/admin/home")) {
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("/view/admin/home.jsp").forward(req, resp);
        }
    }
}
