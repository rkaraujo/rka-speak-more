import React from 'react';
import axios from 'axios';

class PlanSelect extends React.Component {
    state = { plans: [], selectedPlanId: 0 };

    onPlanSelect = (event) => {
        const planId = event.target.value;
        this.setState({ selectedPlanId: planId });
        this.props.onPlanSelect(planId);
    }

    componentDidMount() {
        axios.get('/v1/phone-plans')
            .then((resp) => {
                const plans = resp.data;
                this.setState({ plans });
                if (plans.length > 0) {
                    this.setState({ selectedPlanId: plans[0].id });
                    this.props.onPlanSelect(plans[0].id);
                }
            })
            .catch((error) => console.error("Error loading plans, error =", error));
    }

    render() {
        const plans = this.state.plans;
        const planId = this.state.selectedPlanId;

        let planOptions = <option>Carregando...</option>;
        if (plans.length > 0) {
            planOptions = plans.map((plan) => {
                return <option key={plan.id} value={plan.id}>{plan.name}</option>;
            });
        }

        return (
            <div className="eight wide field">
                <label>Plano:</label>
                <select name="planId" value={planId} onChange={this.onPlanSelect}>
                    {planOptions}
                </select>
            </div>
        );
    }

}

export default PlanSelect;
