import * as controller from "./controllers/login.controller.mjs";

export const handler = async (event) => {
  try {
    const method = event.requestContext.http.method;

    if (method === "POST") return controller.login(event);

    return {
      statusCode: 405,
      body: JSON.stringify({ message: "Method Not Allowed" })
    };

  } catch (error) {
    console.error("Unexpected Error:", error);
    return {
      statusCode: 500,
      body: JSON.stringify({ message: "Internal Server Error" })
    };
  }
};
