package edu.csula.datascience.acquisition;

import java.util.Collection;

import java.util.Iterator;

/**
 * Data source interface
 */
/*
 * public interface Source<T> { Collection<T> provide(); }
 */

public interface Source<T> extends Iterator<Collection<T>> {
}
