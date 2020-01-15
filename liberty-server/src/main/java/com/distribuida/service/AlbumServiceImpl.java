package com.distribuida.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.distribuida.dto.Album;

@Dependent
public class AlbumServiceImpl implements AlbumService{
	
	@Inject
	private EntityManager em;

	@Override
	public Album guardar(Album album) {
		em.getTransaction().begin();
		em.persist(album);
		em.getTransaction().commit();
		return album;
	}

	@Override
	public Album editar(Album album) {
		em.getTransaction().begin();
		em.merge(album);
		em.getTransaction().commit();
		return album;
	}

	@Override
	public void eliminar(Integer id) {
		em.getTransaction().begin();
		Album album = em.createQuery("SELECT u FROM Album u WHERE id = :id", Album.class).setParameter("id", id).getResultList().get(0);
		em.remove(album);
		em.getTransaction().commit();
	}

	@Override
	public Album buscarPorId(Integer id) {
		List<Album> albums;
		albums = em.createQuery("SELECT u FROM Album u WHERE id = :id", Album.class).setParameter("id", id).getResultList();
		return albums.get(0);
	}

	@Override
	public List<Album> listarTodos() {
		List<Album> albums;
		albums = em.createQuery("SELECT u FROM Album u", Album.class).getResultList();
		return albums;
	}
}
