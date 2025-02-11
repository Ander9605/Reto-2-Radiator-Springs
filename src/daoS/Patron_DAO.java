package daoS;

import java.util.ArrayList;

public interface Patron_DAO<TipoGen> {
	/**
	 * INSERTAR
	 * @param t
	 * @return
	 */
	public boolean insertar(TipoGen t); // Insertar un registro (del tipo que sea)

	/**
	 * BORRAR
	 * @param pk
	 * @return
	 */
	public boolean borrar(Object pk); // Eliminar un registro referenciado por su PK

	/**
	 * ACTUALIZAR
	 * @param t
	 * @return
	 */
	public boolean actualizar(TipoGen t); // Actualizar un registro

	/**
	 * BUSCAR
	 * @param pk
	 * @return
	 */
	public TipoGen buscar(Object pk); // Devuelve el registro cuya PK se le pasa

	/**
	 * LISTAR TODO
	 * @return
	 */
	public ArrayList<TipoGen> listarTodos();// Devuelve la lista de todos los registros de la tabla
}
