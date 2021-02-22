import './App.css';
import {ClientForm} from "./ClientForm";
import {ClientsViewer} from "./ClientsViewer";
import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";


function App() {
    return (
        <Router>
            <div className="App">
                <header className="App-header">
                    <Switch>
                        <Route path="/clients/:clientId" component={ClientForm}/>
                        <Route path="/clients" component={ClientsViewer}/>
                    </Switch>
                </header>
            </div>
        </Router>
    );
}

export default App;
