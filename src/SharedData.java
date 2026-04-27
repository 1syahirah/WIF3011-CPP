public class SharedData {
   // The volatile keyword guarantees memory visibility across threads.
    // It ensures the CheckTask instantly sees updates made by the IncrementTask.
    public volatile int counter = 0; 
}
