package filter;

import java.io.IOException;

import entity.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebFilter("/*")
public class RoleFilter implements Filter{
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();

        // Cho phép truy cập các trang public (login, css/js/images)
        if (uri.endsWith("login") || uri.endsWith("login.jsp") || uri.contains("/css/") || uri.contains("/js/")) {
            chain.doFilter(request, response);
            return;
        }

        // Kiểm tra đăng nhập
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        int roleId = user.getRoleId();

        // Lọc theo role
        if (uri.startsWith(req.getContextPath() + "/admin")) {
            if (roleId != 3) {
                resp.sendRedirect(req.getContextPath() + "/access-denied.jsp");
                return;
            }
        } else if (uri.startsWith(req.getContextPath() + "/manager")) {
            if (roleId != 2) {
                resp.sendRedirect(req.getContextPath() + "/access-denied.jsp");
                return;
            }
        } else if (uri.startsWith(req.getContextPath() + "/user")) {
            if (roleId != 1) {
                resp.sendRedirect(req.getContextPath() + "/access-denied.jsp");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
