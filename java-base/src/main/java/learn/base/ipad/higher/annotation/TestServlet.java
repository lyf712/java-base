package learn.base.ipad.higher.annotation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import javax.servlet.annotation.WebServlet;
/**
 * @AUTHOR LYF
 * @DATE 2021/5/15
 * @VERSION 1.0
 * @DESC
 */
//@WebServlet("/")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        super.doGet(req, resp);
    }
}
