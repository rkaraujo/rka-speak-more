import React from 'react';
import { formatMoney } from '../util/formatter';

const ShowCallCost = (props) => {
    const costWithPlan = formatMoney(props.costWithPlan);
    const costWithoutPlan = formatMoney(props.costWithoutPlan);

    if (costWithPlan === '' && costWithoutPlan === '') {
        return null;
    }

    return (
        <div className="ui segment center aligned">
            <h2>Custo da Ligação</h2>
            <div className="ui centered stackable grid">
                <div className="ui statistic six wide column">
                    <div className="value">
                        {costWithPlan}
                    </div>
                    <div className="label">
                        Com o plano FaleMais
                    </div>
                </div>
                <div className="ui statistic six wide column">
                    <div className="value">
                        {costWithoutPlan}
                    </div>
                    <div className="label">
                        Sem o plano FaleMais
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ShowCallCost;
