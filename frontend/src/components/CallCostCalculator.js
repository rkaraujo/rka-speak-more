import React from 'react';
import PlanSelect from './PlanSelect';
import InputDDD from './InputDDD';
import InputTime from './InputTime';
import './CallCostCalculator.css';

const CallCostCalculator = (props) => (
    <div className="ui segment call-cost-calculator">
        <div className="ui attached message">
            <div className="header">
                Calcule o custo da ligação
            </div>
            <p>Escolha o plano, o DDD de origem e destino e o tempo da ligação para saber o custo</p>
        </div>

        <form className={'ui attached form segment' + (props.loading ? ' loading' : '')} onSubmit={props.onSubmitCallCostCalculator}>
            <PlanSelect onPlanSelect={props.onPlanSelect} />
            <div className="three fields">
                <InputDDD name="sourceDDD" label="DDD Origem" onDDDChange={props.onSourceDDDChange} />
                <InputDDD name="destinationDDD" label="DDD Destino" onDDDChange={props.onDestinationDDDChange} />
                <InputTime onTimeChange={props.onTimeChange} />
            </div>
            <input className="ui blue button" type="submit" value="Calcular" />
        </form>
    </div>
);

export default CallCostCalculator;