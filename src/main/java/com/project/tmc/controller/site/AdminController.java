package com.project.tmc.controller.site;

import com.project.tmc.model.User;
import com.project.tmc.service.RoleService;
import com.project.tmc.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Класс контроллер для админ панили
 */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    /**
     * @return страница админ панели
     */
    @GetMapping("")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.findAll());

        return "admin/AdminPage";
    }

    /**
     * @return страница добавления нового пользователя
     */
    @GetMapping("/user")
    public String addPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles_list", roleService.findAll());
        return "admin/add/AddUser";
    }

    /**
     * @return страница редактирования пользователя
     */
    @GetMapping("/user/{id}")
    public String showUpdateUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id).orElseThrow());
        model.addAttribute("roles_list", roleService.findAll());
        return "admin/add/AddUser";
    }

    /**
     * Что-то похожее на <b>API</b>
     * Добавление нового пользователя
     * @return перенаправление на админ панель
     */
    @PostMapping("/user")
    public String addUser(@ModelAttribute User newUser) {
        try {
            userService.insertUser(newUser);
            log.info("Add new user");
            return "redirect:/admin?success";
        } catch (Exception e) {
            log.error(e.getMessage());
            return String.format("redirect:/error-page?error_code=500&error_text=%s&previous_page=/admin", e.getMessage());
        }
    }


    /**
     * Что-то похожее на <b>API</b>
     * Удаление пользователя
     * @return перенаправление на админ панель
     */
    @PostMapping(value="/user/{id}", params = "delete")
    public String deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return "redirect:/admin?success";
        } catch (Exception e) {
            log.error(e.getMessage());
            return String.format("redirect:/error-page?error_code=500&error_text=%s&previous_page=/admin", e.getMessage());
        }
    }

    /**
     * Что-то похожее на <b>API</b>
     * Редактирование пользователя
     * @return перенаправление на админ панель
     */
    @PostMapping(value="/user/{id}", params = "put")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute User user,
                             Model model) {
        try {
            userService.updateUser(user);
            return "redirect:/admin?success";
        } catch (Exception e) {
            log.error(e.getMessage());
            return String.format("redirect:/error-page?error_code=500&error_text=%s&previous_page=/admin", e.getMessage());
        }
    }
}
