const toCamelCaseKeyObject = (param: any): any => {
  if (Array.isArray(param)) {
    return param.map((item) => toCamelCaseKeyObject(item));
  }

  if (param instanceof Object) {
    return Object.keys(param).reduce((obj: any, key) => {
      const newKey = key.slice(0, 1).toLowerCase() + key.slice(1);
      // eslint-disable-next-line no-param-reassign
      obj[newKey] = toCamelCaseKeyObject(param[key]);
      return obj;
    }, {});
  }

  return param;
};

export { toCamelCaseKeyObject };
