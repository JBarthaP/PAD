
const KEY_VALUE = "AIzaSyAXEcmlI4XnU3lADHcwZGBiIemMm8zXjzw";

const BASE_URL = "https://www.googleapis.com/books/v1/volumes?"

const QUERY_PARAM = "q";

const PRINT_TYPES = "printType";

const PRINT_TYPES_VALUE = "books"

const MAX_RESULTS = "maxResults";

const KEY = "key";

const MAX_RESULTS_VALUE = "40";

export const buildURI = (query) => {
    const buildURI = new URL(BASE_URL)
    buildURI.searchParams.append(QUERY_PARAM, query);
    buildURI.searchParams.append(PRINT_TYPES, PRINT_TYPES_VALUE);
    buildURI.searchParams.append(MAX_RESULTS, MAX_RESULTS_VALUE);
    buildURI.searchParams.append(KEY, KEY_VALUE);

    return buildURI;
}


