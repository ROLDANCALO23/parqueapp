import { GetCommand } from "@aws-sdk/lib-dynamodb";
import { ddb } from "../config/dynamo.mjs";

const TABLE = process.env.TABLE_NAME;

export const getUsuarioByEmail = (email) =>
  ddb.send(new GetCommand({
    TableName: TABLE,
    Key: { email },
  }));
