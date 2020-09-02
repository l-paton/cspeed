package paton.laura.velocidaddecalculo;

import android.content.Context;

import java.util.Random;

public class Partida {

    private final char[] tipoOperacion = {'+', '-','*','/'};
    private int resultadoGenerado, resultadoIntroducido;
    private int n1,n2,aciertos, fallos;
    private char operacion;

    public Partida(){
        aciertos = 0;
        fallos = 0;
    }

    public String generarOperacion(){
        int n;
        operacion = tipoOperacion[generarNumero(2)];

        switch (operacion){
            case '+':
                n1 = generarNumero(1000);
                n2 = generarNumero(1000);
                resultadoGenerado = n1+n2;
                break;
            case '-':
                n1 = generarNumero(1000);
                n2 = generarNumero(1000);
                resultadoGenerado = n1-n2;
                break;
            case '*':
                n1 = generarNumero(100);
                n2 = generarNumero(10);
                resultadoGenerado = n1*n2;
                break;
        }

        return String.valueOf(n1) + operacion + String.valueOf(n2);
    }

    public int generarNumero(int n){
        Random dado = new Random();
        int numero = dado.nextInt(n)+1;
        return numero;
    }

    public boolean comprobarResultado(){
        if(resultadoGenerado == resultadoIntroducido) return true;
        else return false;
    }

    public void sumarAcierto(Context c){
        Game.partida.setAciertos(Game.partida.getAciertos()+1);
        Game.pantallaAciertos.setText(c.getString(R.string.aciertos, getAciertos()));
        Game.nuevaOperacion(c);
    }

    public void sumarFallo(Context c){
        Game.partida.setFallos(Game.partida.getFallos()+1);
        Game.pantallaFallos.setText(c.getString(R.string.fallos, getFallos()));
        Game.nuevaOperacion(c);
    }

    public int getResultadoGenerado() {
        return resultadoGenerado;
    }

    public void setResultadoGenerado(int resultadoGenerado) {
        this.resultadoGenerado = resultadoGenerado;
    }

    public int getResultadoIntroducido() {
        return resultadoIntroducido;
    }

    public void setResultadoIntroducido(int resultadoIntroducido) {
        this.resultadoIntroducido = resultadoIntroducido;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public char getOperacion() {
        return operacion;
    }

    public void setOperacion(char operacion) {
        this.operacion = operacion;
    }
}
