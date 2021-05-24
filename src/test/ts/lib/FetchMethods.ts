function Post(url: string, data: any, headers: HeadersInit={}): Promise<Response> {
  return fetch(url, {
    method: "POST",
    
    headers: {
      "Content-Type": "application/json",
      ...headers
    },
    body: JSON.stringify(data),
  });
}

function Delete(url: string, headers: HeadersInit={}): Promise<Response> {
  return fetch(url, { method: "DELETE", ...headers });
}

function Put(url: string, data: any, headers: HeadersInit={}): Promise<Response> {
  return fetch(url, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      ...headers
    },
    body: JSON.stringify(data),
  });
}
export {Post,Delete,Put};