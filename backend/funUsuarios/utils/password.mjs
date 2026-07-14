import { scryptSync, randomBytes, timingSafeEqual } from "crypto";

// ponytail: scrypt nativo de Node (sin dependencias); costo fijo por defecto,
// suficiente para un proyecto académico. Si se necesita ajustar el costo
// computacional a futuro, pasar opciones a scryptSync (N, r, p).
const KEY_LENGTH = 64;

export const hashPassword = (password) => {
  const salt = randomBytes(16).toString("hex");
  const hash = scryptSync(password, salt, KEY_LENGTH).toString("hex");
  return `${salt}:${hash}`;
};

export const verifyPassword = (password, stored) => {
  const [salt, hash] = (stored || "").split(":");
  if (!salt || !hash) return false;

  const hashBuffer = Buffer.from(hash, "hex");
  const candidate = scryptSync(password, salt, KEY_LENGTH);
  if (candidate.length !== hashBuffer.length) return false;

  return timingSafeEqual(candidate, hashBuffer);
};
