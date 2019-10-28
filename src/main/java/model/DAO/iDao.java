package model.DAO;

import java.util.List;

/**
 *
 * @author joseo
 */
public interface iDao<T, TypeID> {
    
    public List<T> getAll(int limit);
    public T consultarPorId(TypeID id);
    public void excluir(TypeID id);
    public T salvar(T t) throws Exception;
    
}