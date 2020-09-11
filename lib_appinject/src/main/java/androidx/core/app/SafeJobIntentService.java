package androidx.core.app;

public abstract class SafeJobIntentService extends JobIntentService{
    
    @Override
    GenericWorkItem dequeueWork(){
        try{
            return super.dequeueWork();
        }catch(SecurityException e){
            e.printStackTrace();
            return null;
        }
    }
}