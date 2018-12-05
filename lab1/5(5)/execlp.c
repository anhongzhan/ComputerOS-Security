#include <unistd.h>  
  
int main()  
{  
        execlp("ls", "ls", "-l", "/etc", (char *)0);  
        return 0;  
}  
