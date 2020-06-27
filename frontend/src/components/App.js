import React from 'react';
import axios from 'axios';
import Header from './Header';
import CallCostCalculator from './CallCostCalculator';
import ShowCallCost from './ShowCallCost';
import Message from './Message';

class App extends React.Component {
    state = {
        planId: 0,
        sourceDDD: '',
        destinationDDD: '',
        time: '',
        costWithPlan: '',
        costWithoutPlan: '',
        loading: false,
        errorMessage: ''
    };

    onPlanSelect = (planId) => {
        this.setState({ planId });
    }

    onSourceDDDChange = (sourceDDD) => {
        this.setState({ sourceDDD });
    }

    onDestinationDDDChange = (destinationDDD) => {
        this.setState({ destinationDDD });
    }

    onTimeChange = (time) => {
        this.setState({ time });
    }

    onSubmitCallCostCalculator = (event) => {
        event.preventDefault();
        this.setState({
            costWithPlan: '',
            costWithoutPlan: '',
            loading: true,
            errorMessage: ''
        });

        axios.get('/v1/phone-call/cost', {
            params: {
                planId: this.state.planId,
                sourceDDD: this.state.sourceDDD,
                destinationDDD: this.state.destinationDDD,
                time: this.state.time
            },
            timeout: 500
        })
            .then((resp) => {
                const { costWithPlan, costWithoutPlan } = resp.data;
                this.setState({ costWithPlan, costWithoutPlan, loading: false });
            })
            .catch((error) => {
                let errorMessage = 'Ocorreu um erro';
                if (error.response) {
                    errorMessage = error.response.data.message;
                    console.error(error.response);
                } else if (error.message) {
                    errorMessage = error.message;
                    console.error(error.message);
                }
                
                this.setState({
                    loading: false,
                    errorMessage
                });
            });
    }

    render() {
        return (
            <div className="ui container">
                <Header />

                <CallCostCalculator onPlanSelect={this.onPlanSelect}
                    onSourceDDDChange={this.onSourceDDDChange}
                    onTimeChange={this.onTimeChange}
                    onDestinationDDDChange={this.onDestinationDDDChange}
                    onSubmitCallCostCalculator={this.onSubmitCallCostCalculator}
                    loading={this.state.loading} />

                <ShowCallCost costWithPlan={this.state.costWithPlan} costWithoutPlan={this.state.costWithoutPlan} />

                <Message message={this.state.errorMessage} />
            </div>
        );
    }

}

export default App;
