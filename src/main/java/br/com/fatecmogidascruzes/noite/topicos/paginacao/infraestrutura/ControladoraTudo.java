package br.com.fatecmogidascruzes.noite.topicos.paginacao.infraestrutura;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladoraTudo", urlPatterns = {"/musicasAll"})
public class ControladoraTudo extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        Gson gson = new Gson();
        EntityManagerFactory fabricaDAOGenerico = Persistence.createEntityManagerFactory("AplicacaoBancariaPU");
        EntityManager entityManager = fabricaDAOGenerico.createEntityManager(); 
        List<Musica> musicas = entityManager.createQuery("SELECT m FROM Musica m", Musica.class)
        .getResultList();
        String json = gson.toJson(musicas);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
