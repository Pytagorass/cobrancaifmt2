package edu.ifmt.cobrancaifmt2.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.ifmt.cobrancaifmt2.Titulos;
import edu.ifmt.cobrancaifmt2.model.Titulo;



@Controller
class TituloController {

    // Repositorio Spring Data usado para gravar e consultar titulos no banco.
    private final Titulos titulos;

    // Injecao de dependencia: o Spring entrega uma instancia de Titulos ao controller.
    public TituloController(Titulos titulos) {
        this.titulos = titulos;
    }

    // Abre a tela de cadastro quando o usuario acessa /titulos/novo.
    @RequestMapping("/titulos/novo")
    public String novo() {
        return "CadastroTitulo";
    }

    // Recebe os dados enviados pelo formulario e salva o titulo no banco.
    @RequestMapping(value = "/titulos", method = RequestMethod.POST)
    public ModelAndView salvar(Titulo titulo) {
        titulos.save(titulo);

        // Volta para a mesma tela exibindo uma mensagem de sucesso.
        ModelAndView mv = new ModelAndView("CadastroTitulo");
        mv.addObject("mensagem", "Título salvo com sucesso!");
        return mv;
    }
}
