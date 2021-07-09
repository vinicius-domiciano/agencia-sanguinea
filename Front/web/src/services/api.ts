class Api {
    public static BASE_URL: string = "http://localhost:8080";
    private url: string;

    private headers: Headers = new Headers();

    constructor(uri: string) {
        this.url = `${Api.BASE_URL}${uri}`;

        this.headers.append("Content-Type", "application/json");
        this.headers.append("Accept", "*/*");
        this.headers.append("X-Custom-Header", "ProcessThisImmediately");

    }

    get(): Promise<any> {

        const config: RequestInit = {
            method: "GET",
            mode: "cors",
        };

        return this.request(config, 200);
    }

    post(body: any): Promise<any> {

        const jsonString: String = JSON.stringify(body);

        const config: RequestInit = {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Content-Length': jsonString.length.toString()
            },
            method: "POST",
            mode: "cors",
            body
        };

        return this.request(config, 201);
    }

    put(body: any): Promise<any> {

        const config: RequestInit = {
            headers: this.headers,
            method: "PUT",
            mode: "cors",
            body
        };

        return this.request(config, 204);
    }

    private request(config: RequestInit, statusReturn: number): Promise<any> {

        const request = new Request(this.url, config);

        return fetch(request).then(response => {
            console.info(`Request http(s) [${config.method}], response status [${response.status}]`);
            if (response.status >= 500)
                throw new Error(`Problemas na requisições, httpStatus: [${response.status}]`);

            return response.json();
        }).then(data => {
            return data;
        }).catch((erro: Error) => {
            console.info(`Erro ao realizar requizição: ${erro.message}`);
            return { error: "Server internal error" }
        })
    }

}

export default Api;