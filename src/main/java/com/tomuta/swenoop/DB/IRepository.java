package com.tomuta.swenoop.DB;

import java.util.List;
import java.util.UUID;

public interface IRepository<T>  {
    boolean delete(T toDelete);
    boolean delete(UUID id);
    T GetById(UUID id);
    T Add(T toAdd);
    T Update(T toUpdate);
    List<T> GetAll();
}
