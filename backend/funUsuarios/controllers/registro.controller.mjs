import * as service from "../services/registro.service.mjs";
import { registroSchema } from "../validators/registro.schema.mjs";
import { ok, badRequest, conflict } from "../utils/response.mjs";

export const registrar = async (event) => {
  const body = JSON.parse(event.body || "{}");
  const parsed = registroSchema.safeParse(body);

  if (!parsed.success) return badRequest(parsed.error);

  const resultado = await service.registrar(parsed.data);

  if (!resultado.success) {
    return conflict({ message: resultado.message });
  }

  console.log(`Registro exitoso - email: ${parsed.data.email}`);
  return ok(resultado.data);
};
