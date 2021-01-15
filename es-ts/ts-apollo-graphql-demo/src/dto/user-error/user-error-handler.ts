import { map, pick } from "ramda";
import { ValidationError } from "../../validators";
import { UserError } from "./user-error";

type HandleErrors = (error: Record<string, any>) => UserError[];

export const handleErrors: HandleErrors = (error) => {
  if (error.name !== "ValidationError") throw error;

  const validationError = error as ValidationError;

  if (validationError.inner.length > 0) {
    return map(pick(["path", "message"]), validationError.inner);
  }

  return [pick(["path", "message"], validationError)];
};
