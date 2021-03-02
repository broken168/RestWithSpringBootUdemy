package com.course.udemy.utils;

import com.course.udemy.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

@Service
public class UtilsMath {



    public Double soma(String numberOne, String numberTwo) throws Exception {
        if(isNumeric(numberOne) && isNumeric(numberTwo)){
            return convertToDouble(numberOne) + convertToDouble(numberTwo);
        }else {
            throw new UnsupportedMathOperationException("Digite um valor número, pls");
        }
    }

    public Double subtracao(String numberOne, String numberTwo) throws Exception {
        if(isNumeric(numberOne) && isNumeric(numberTwo)){
            return convertToDouble(numberOne) - convertToDouble(numberTwo);
        }else {
            throw new UnsupportedMathOperationException("Digite um valor número, por favor");
        }
    }

    public Double multiplicacao(String numberOne, String numberTwo) throws Exception {
        if(isNumeric(numberOne) && isNumeric(numberTwo)){
            return convertToDouble(numberOne) * convertToDouble(numberTwo);
        }else {
            throw new UnsupportedMathOperationException("Digite um valor número, pls");
        }
    }

    public Double divisao(String numberOne, String numberTwo) throws Exception {
        if(isNumeric(numberOne) && isNumeric(numberTwo)){
            return convertToDouble(numberOne) / convertToDouble(numberTwo);
        }else {
            throw new UnsupportedMathOperationException("Digite um valor número, pls");
        }
    }

    public Double media(String numberOne, String numberTwo) throws Exception {
        if(isNumeric(numberOne) && isNumeric(numberTwo)){
            return ((convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2);
        }else {
            throw new UnsupportedMathOperationException("Digite um valor número, pls");
        }
    }

    public Double raizQuadrada(String numberOne) throws Exception {
        if(isNumeric(numberOne)){
            return Math.sqrt(convertToDouble(numberOne));
        }else {
            throw new UnsupportedMathOperationException("Digite um valor número, pls");
        }
    }

    private Double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D;
        String number = strNumber.replace(",", ".");
        if(isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    private boolean isNumeric(String strNumber) {
        String number = strNumber.replace(",", ".");

        if(number.matches("(([-+])?[0-9]+(\\.[0-9]+)?)+")){
            return true;
        }

        return false;
    }
}
