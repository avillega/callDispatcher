# Dispatcher

## Introduction

Source code for almundo application.

### Extras
1. When more than ten calls are made the extra calls are put on hold and delivered one by one when
threads finish their work. This is the expected behaviour of the fixedThreadPool. In case the calls want to be
attended as they come (No queuing) a ThreadPool with growing policy could be  created

1. In case there is no employees free to take the call the thread gets blocked until a worker is available
. This was implemented using a PriorityBlockingQueue which keeps the items ordered (in this case Operators first, then 
supervisors, then directors) and also blocks if there is no items in the list. Since there is a fixed number of
workers that once finished a call are pushed to the queue again, all calls will be processed   
