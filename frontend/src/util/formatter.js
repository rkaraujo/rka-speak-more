const formatMoney = (num) => {
    if (num !== '') {
        return 'R$ ' + Number(num).toFixed(2).replace('.', ',');
    }
    return num;
}

export {
    formatMoney
}