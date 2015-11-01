package dnomyar.rxgag.rxflux.dispatcher;


import dnomyar.rxgag.rxflux.action.RxAction;

/**
 * Created by marcel on 10/09/15.
 */
public interface RxActionDispatch {

    void onRxAction(RxAction action);

}
