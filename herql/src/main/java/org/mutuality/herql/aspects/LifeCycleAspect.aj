package org.mutuality.herql.aspects;



public aspect LifeCycleAspect {

	public enum State {
        INITIAL,
        INITIALIZING,INITIALIZED,
        STARTING,STARTED,
        STOPPING,
        TERMINATING,TERMINATED,
        BROKEN;
    }

	public interface Lifecycle {
        void initialize();
        void start();
        void stop();
        void terminate();
        boolean isBroken();
        State getState();
    }
	
	public interface Persistance {
		void save();
		void load();
		void find();
	}

	declare parents : @ManagedComponent * implements Lifecycle, Persistance;
    
    private State Lifecycle.state = State.INITIAL;
    public void Lifecycle.initialize() {}
    public void Lifecycle.start() {}
    public void Lifecycle.stop() {}
    public void Lifecycle.terminate() {}
    public boolean Lifecycle.isBroken() { return state == State.BROKEN; }
    public State Lifecycle.getState() { return state; }

    public void Persistance.save() {}
    public void Persistance.load() {}
    public void Persistance.find() {}
    
    // these pointcuts capture the lifecycle events of managed components
    pointcut initializing(Lifecycle l) : 
      execution(* Lifecycle.initialize(..)) && this(l);
    pointcut starting(Lifecycle l) : 
      execution(* Lifecycle.start(..)) && this(l);
    pointcut stopping(Lifecycle l) : 
      execution(* Lifecycle.stop(..)) && this(l);
    pointcut terminating(Lifecycle l): 
      execution(* Lifecycle.terminate(..)) && this(l);
    
    pointcut querying(Lifecycle l): 
        execution(State Lifecycle.getState()) && this(l);
        
    /**
     * Ensure we are in the initial state before initializing.
     */
    before(Lifecycle managedComponent) : initializing(managedComponent) {
        if (managedComponent.state != State.INITIAL)
            throw new IllegalStateException("Can only initialize from INITIAL state");
        managedComponent.state = State.INITIALIZING;
    }
    
    /**
     * If we successfully initialized the component, update the state and
     * notify all observers.
     */
    after(Lifecycle managedComponent) returning : initializing(managedComponent) {
        managedComponent.state = State.INITIALIZED;
        System.out.println("initializing");
    }
    
    /**
     * Ensure we are in the initial state before initializing.
     */
    before(Lifecycle managedComponent) : starting(managedComponent) {
        if (managedComponent.state != State.INITIALIZED)
            throw new IllegalStateException("Can only initialize from INITIAL state");
        managedComponent.state = State.STARTING;
    }
    
    /**
     * If we successfully initialized the component, update the state and
     * notify all observers.
     */
    after(Lifecycle managedComponent) returning : starting(managedComponent) {
        managedComponent.state = State.STARTED;
        System.out.println("starting");
    }
    
    /**
     * Ensure we are in the initial state before initializing.
     */
    before(Lifecycle managedComponent) : stopping(managedComponent) {
        if (managedComponent.state != State.STARTED)
            throw new IllegalStateException("Can only initialize from INITIAL state");
        managedComponent.state = State.STOPPING;
    }
    
    /**
     * If we successfully initialized the component, update the state and
     * notify all observers.
     */
    after(Lifecycle managedComponent) returning : stopping(managedComponent) {
        managedComponent.state = State.STOPPING;
        System.out.println("stopping " + managedComponent.getClass());
    }
    
    /**
     * Ensure we are in the initial state before initializing.
     */
    before(Lifecycle managedComponent) : terminating(managedComponent) {
        if (managedComponent.state != State.STOPPING)
            throw new IllegalStateException("Can only initialize from INITIAL state");
        managedComponent.state = State.TERMINATING;
    }
    
    /**
     * If we successfully initialized the component, update the state and
     * notify all observers.
     */
    after(Lifecycle managedComponent) returning : terminating(managedComponent) {
        managedComponent.state = State.TERMINATED;
        System.out.println("terminating");
    }
    
    State around(Lifecycle l)  : querying(l){
    	System.out.println("Querying");
    	return l.state;
    }

}
