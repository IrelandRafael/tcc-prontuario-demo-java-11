package com.unigranead.tcc.pages;

import java.io.IOException;
import java.util.List;

import com.unigranead.tcc.entities.Login;
import com.unigranead.tcc.entities.Paciente;
import com.unigranead.tcc.services.LoginServices;
import com.unigranead.tcc.services.PacienteServices;
import com.unigranead.tcc.services.ProntuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;

@RestController
public class PacienteController {

  @Autowired
  PacienteServices pacienteServices;

  @Autowired
  ProntuarioServices prontuarioServices;

  @Autowired
  LoginServices loginServices;

  @GetMapping(value = "/list-pacientes")
  public ModelAndView getAll(String searchValue) {
    ModelAndView modelAndView = new ModelAndView();
    List<Paciente> pacientes;
    if (searchValue != null && !searchValue.isEmpty()) {
      pacientes = pacienteServices.findByNomeLike(searchValue);
    } else {
      pacientes = pacienteServices.findAll();
    }
    modelAndView.addObject("searchValue", searchValue);
    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("pacientes", pacientes);
    modelAndView.setViewName("list-pacientes");
    return modelAndView;
  }

  @GetMapping(value = "/view-paciente/{id}")
  public ModelAndView viewPaciente(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.addObject("imgUtil", new ImageUtil());
    modelAndView.setViewName("view-paciente");
    return modelAndView;
  }

  @GetMapping(value = "/historico-paciente-form/{id}")
  public ModelAndView viewHistorico(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("view-historico");
    return modelAndView;
  }

  @GetMapping(value = "/medicamento-paciente-form/{id}")
  public ModelAndView viewMedicamento(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("view-medicamento");
    return modelAndView;
  }

  @GetMapping(value = "/exame-paciente-form/{id}")
  public ModelAndView viewExame(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.addObject("imgUtil", new ImageUtil());
    modelAndView.setViewName("view-exame");
    return modelAndView;
  }

  @GetMapping(value = "/diag-paciente-form/{id}")
  public ModelAndView viewDiagnostico(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("view-diagnostico");
    return modelAndView;
  }

  @GetMapping(value = "/edit-paciente-form/{id}")
  public ModelAndView editPacienteForm(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.addObject("imgUtil", new ImageUtil());
    modelAndView.setViewName("edit-paciente");
    return modelAndView;
  }

  @PostMapping(value = "/atualizar-paciente")
  public ModelAndView updatePaciente(Paciente paciente, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView(new RedirectView());
    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("edit-paciente");
    } else {
      pacienteServices.update(paciente.getIdPaciente(), paciente);
      modelAndView.addObject("successMessage", "Paciente atualizado com sucesso");
      modelAndView.setViewName("redirect:edit-paciente-form/"+paciente.getIdPaciente());
    }
    return modelAndView;
  }

  @GetMapping(value = "/delete-paciente/{id}")
  public ModelAndView deletePaciente(@PathVariable("id") Integer id) {
    Login login = pacienteServices.findById(id).getLogin();
    pacienteServices.delete(id);
    loginServices.delete(login.getIdLogin());

    List<Paciente> pacientes = pacienteServices.findAll();
    ModelAndView model = new ModelAndView(new RedirectView("/list-pacientes"));
    model.addObject("pacientes", pacientes);
    return model;
  }

  @GetMapping(value = "/edit-historico-paciente-form/{id}")
  public ModelAndView editHistoricoPacienteForm(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("edit-historico");
    return modelAndView;
  }

  @PostMapping(value = "/atualizar-prontuario")
  public ModelAndView atualizarProntuario(Paciente paciente) {
    ModelAndView modelAndView = new ModelAndView(new RedirectView());
    prontuarioServices.update(paciente.getIdPaciente(), paciente.getProntuario());
    modelAndView.addObject("successMessage", "Prontuario atualizado com sucesso");
    modelAndView.setViewName("redirect:edit-paciente-form/"+paciente.getIdPaciente());
    return modelAndView;
  }

  @GetMapping(value = "/edit-medicamento-paciente-form/{id}")
  public ModelAndView editMedicamentoPacienteForm(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("edit-medicamento");
    return modelAndView;
  }

  @GetMapping(value = "/edit-exame-paciente-form/{id}")
  public ModelAndView editExamePacienteForm(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.addObject("imgUtil", new ImageUtil());
    modelAndView.setViewName("edit-exame");
    return modelAndView;
  }

  @GetMapping(value = "/edit-diag-paciente-form/{id}")
  public ModelAndView editDiagPacienteForm(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("edit-diagnostico");
    return modelAndView;
  }

  @PostMapping(value = "/atualizar-exame")
  public ModelAndView atualizarExame(@RequestParam("file") MultipartFile file, Paciente paciente) {
    ModelAndView modelAndView = new ModelAndView(new RedirectView());

    Paciente pacienteLoad = pacienteServices.findById(paciente.getIdPaciente());

    try {
      pacienteLoad.getProntuario().setExame(file.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }

    prontuarioServices.update(paciente.getIdPaciente(), pacienteLoad.getProntuario());
    modelAndView.addObject("successMessage", "Prontuario atualizado com sucesso");
    modelAndView.setViewName("redirect:edit-paciente-form/"+paciente.getIdPaciente());


    return modelAndView;
  }

  @GetMapping(value = "/criar-paciente-form")
  public ModelAndView criarPacienteForm() {
    ModelAndView modelAndView = new ModelAndView();
    Paciente p = new Paciente();

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", p);
    modelAndView.setViewName("criar-paciente");
    return modelAndView;
  }

  @PostMapping(value = "/criar-paciente")
  public ModelAndView criarPacienteForm(@RequestParam("file") MultipartFile file, @Valid Paciente paciente, BindingResult result) {
    ModelAndView modelAndView = new ModelAndView();

    if (result.hasErrors()) {
      modelAndView.setViewName("criar-paciente");
      return modelAndView;
    }

    try {
      paciente.setFoto(file.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
    pacienteServices.insert(paciente);

    List<Paciente> pacientes = pacienteServices.findAll();

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("pacientes", pacientes);

    modelAndView.setViewName("list-pacientes");
    return modelAndView;
  }

  @GetMapping(value = "/edit-conduta-paciente-form/{id}")
  public ModelAndView editCondutaPacienteForm(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("edit-conduta-paciente");
    return modelAndView;
  }

  @GetMapping(value = "/edit-conduta-diaria-paciente-form/{id}")
  public ModelAndView editCondutaDiariaPacienteForm(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("edit-conduta-diaria-paciente");
    return modelAndView;
  }

  @GetMapping(value = "/edit-alerta-paciente-form/{id}")
  public ModelAndView editAlertaPacienteForm(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("edit-alerta-paciente");
    return modelAndView;
  }

  @GetMapping(value = "/view-conduta-paciente-form/{id}")
  public ModelAndView viewCondutaPacienteForm(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("view-conduta-paciente");
    return modelAndView;
  }

  @GetMapping(value = "/view-conduta-diaria-paciente-form/{id}")
  public ModelAndView viewCondutaDiariaPacienteForm(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("view-conduta-diaria-paciente");
    return modelAndView;
  }

  @GetMapping(value = "/view-alerta-paciente-form/{id}")
  public ModelAndView viewAlertaPacienteForm(@PathVariable("id") Integer id) {
    ModelAndView modelAndView = new ModelAndView();
    Paciente paciente = pacienteServices.findById(id);

    modelAndView.addObject("userName",
                           "Bem vindo  " + SecurityContextHolder.getContext().getAuthentication().getName());
    modelAndView.addObject("paciente", paciente);
    modelAndView.setViewName("view-alerta-paciente");
    return modelAndView;
  }
}