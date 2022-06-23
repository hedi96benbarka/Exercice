package com.csys.exercice.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.querydsl.core.types.dsl.BooleanExpression;

  @FunctionalInterface
    public interface LazyBooleanExpression<T>
    {
        BooleanExpression get();
    }