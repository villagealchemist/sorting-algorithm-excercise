I implemented my hybrid sorting method by imitating the code from my randomized quick sort.
Instead of calling it recursively on the quick sort, I recursively call the same concept through hybrid sort.
The recursion stops when the unsorted portion of the list has been cut down to only 10 items and then then I call my
insertion sort algorithm on the remainder of the list. In theory, the hybrid sort would work faster since on smaller lists
the algorithm will preform quicker than the quick sort would. Although it's theta runtime is n^2, it lessens on smaller lists
where quicksort would be less efficient.

I tested both the hybrid sort and the quicksort methods extensively. However, in my experience the quicksort method continuously
outperformed the hybrid sort. On sorted lists the hybrid sort would work faster but on the unsorted lists I tried the quicksort would
outperform the hybrid by >= 100 milliseconds.