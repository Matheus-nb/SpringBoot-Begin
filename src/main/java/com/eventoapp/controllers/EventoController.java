package com.eventoapp.controllers;

import com.eventoapp.models.Evento;
import com.eventoapp.repository.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventoController {

  @Autowired
  private EventoRepository er;

  @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
  public String form() {

    return "evento/formEvento";
  }

  @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
  public String form(Evento evento) {

    er.save(evento);

    return "redirect:/cadastrarEvento";
  }

  @RequestMapping(value = "/eventos", method = RequestMethod.GET)
  public ModelAndView listaEventos() {
    ModelAndView mv = new ModelAndView("index.html");

    Iterable<Evento> eventos = er.findAll();

    mv.addObject("eventos", eventos);

    return mv;
  }

  @RequestMapping(value = "/{codigo}")
  public ModelAndView detalhesEventos(@PathVariable("codigo") long codigo) {
    Evento evento = er.findByCodigo(codigo);

    ModelAndView mv = new ModelAndView("evento/detalhesEvento.html");
    mv.addObject("evento", evento);

    return mv;
  }
}
