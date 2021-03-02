package com.course.udemy.controller;

import com.course.udemy.utils.UtilsMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/math")
public class MathController {

    @Autowired
    private UtilsMath utilsMath;

    @RequestMapping(value="/math/soma/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double soma(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        return utilsMath.soma(numberOne, numberTwo);
    }

    @RequestMapping(value="/math/subtracao/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtracao(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        return utilsMath.subtracao(numberOne, numberTwo);
    }

    @RequestMapping(value="/math/multiplicacao/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplicacao(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        return utilsMath.multiplicacao(numberOne, numberTwo);
    }

    @RequestMapping(value="/math/divisao/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double divisao(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        return utilsMath.divisao(numberOne, numberTwo);
    }

    @RequestMapping(value="/math/media/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double media(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        return utilsMath.media(numberOne, numberTwo);
    }

    @RequestMapping(value="/math/raiz/{numberOne}", method = RequestMethod.GET)
    public Double raizQuadrada(@PathVariable(value = "numberOne") String numberOne) throws Exception {
        return utilsMath.raizQuadrada(numberOne);
    }

}
