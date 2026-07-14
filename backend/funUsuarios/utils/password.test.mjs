// Self-check manual (sin framework). Ejecutar: node password.test.mjs
import assert from "node:assert/strict";
import { hashPassword, verifyPassword } from "./password.mjs";

const hash = hashPassword("clave123");

assert.ok(hash.includes(":"), "el hash debe incluir salt y hash separados por ':'");
assert.equal(verifyPassword("clave123", hash), true, "la password correcta debe verificar");
assert.equal(verifyPassword("otraClave", hash), false, "una password incorrecta no debe verificar");
assert.equal(verifyPassword("clave123", ""), false, "un hash vacío/corrupto no debe verificar");
assert.notEqual(hashPassword("clave123"), hash, "cada hash debe usar un salt distinto");

console.log("OK: password.mjs (hash/verify) funciona correctamente");
