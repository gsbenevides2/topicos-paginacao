package br.com.fatecmogidascruzes.noite.topicos.paginacao.infraestrutura;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladoraPaginacaoAndTimestamp", urlPatterns = {"/musicasPagesTimestamp"})
public class ControladoraPaginadaComTimestamp extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        Gson gson = new Gson();
        int page = Integer.valueOf(request.getParameter("page"));
        Date timestamp = null;
        if(page == 1) {
            timestamp = Date.from(Instant.now());
        }else {
            timestamp = Date.from(Instant.parse(request.getParameter("timestamp")));
        }
        EntityManagerFactory fabricaDAOGenerico = Persistence.createEntityManagerFactory("AplicacaoBancariaPU");
        EntityManager entityManager = fabricaDAOGenerico.createEntityManager(); 
        List<Musica> musicas = entityManager.createQuery("SELECT m FROM Musica m WHERE m.inseridoEm <= :timestamp ORDER BY m.inseridoEm DESC", Musica.class)
        .setMaxResults(10)
        .setParameter("timestamp", timestamp)
        .setFirstResult((page - 1) * 10)
        .getResultList();
        String json = gson.toJson(musicas);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("timestamp", timestamp.toInstant().toString());
        try {
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
