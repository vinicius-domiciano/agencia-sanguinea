import { BrowserRouter, Route } from 'react-router-dom';

import HomePage from './pages/home';

function Routes() {
    return (
        <BrowserRouter>
            <Route path="/" component={HomePage} />
        </BrowserRouter>
    )
}

export default Routes;
