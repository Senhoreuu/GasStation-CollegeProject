package com.GasStation;

import com.GasStation.compositions.FuelType;

public class Tanque {
    private final int id;

    private final FuelType tipoCombustivel;

    private final double capacidade;

    private double quantidade;

    public Tanque(FuelType tipoCombustivel, double capacidade) {
        this.id = (int) (Math.random() * 10000);
        this.tipoCombustivel = tipoCombustivel;
        this.capacidade = capacidade;
        this.quantidade = 0;
    }

    public Tanque() {
        this.id = (int) (Math.random() * 10000);
    }

    public int getId() {
        return id;
    }

    public FuelType getTipoCombustivel() {
        return tipoCombustivel;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public void abastecer(double quantidade) {
        if (this.quantidade + quantidade > capacidade) {
            this.quantidade = capacidade;
        } else {
            this.quantidade += quantidade;
        }
    }
}
