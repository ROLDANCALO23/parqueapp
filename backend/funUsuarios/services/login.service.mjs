import * as repo from "../repositories/usuario.repository.mjs";
import { verifyPassword } from "../utils/password.mjs";

export const login = async ({ email, password }) => {
  const result = await repo.getUsuarioByEmail(email.toLowerCase());
  const usuario = result.Item;

  // No se distingue "usuario no existe" de "password incorrecta": evita
  // que un atacante pueda enumerar correos registrados.
  if (!usuario || !verifyPassword(password, usuario.password)) {
    return null;
  }

  return { email: usuario.email, nombre: usuario.nombre };
};
