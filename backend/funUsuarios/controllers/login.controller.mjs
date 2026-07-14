import * as service from "../services/login.service.mjs";
import { loginSchema } from "../validators/login.schema.mjs";
import { ok, badRequest, unauthorized } from "../utils/response.mjs";

export const login = async (event) => {
  const body = JSON.parse(event.body || "{}");
  const parsed = loginSchema.safeParse(body);

  if (!parsed.success) return badRequest(parsed.error);

  const { email } = parsed.data;
  const usuario = await service.login(parsed.data);

  // Solo se loguea el email y el resultado, nunca la contraseña.
  if (!usuario) {
    console.log(`Login fallido - email: ${email}`);
    return unauthorized();
  }

  console.log(`Login exitoso - email: ${email}`);
  return ok(usuario);
};
