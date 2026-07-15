import { z } from "zod";

export const registroSchema = z.object({
  nombre: z.string().min(3),
  email: z.string().email(),
  telefono: z.string().min(9),
  password: z.string().min(6)
});
