#include <unistd.h>  
  
int main()  
{  
        char * const argv={"ls" "/etc/passwd", NULL};   
　　	char * const envp={"/bin", NULL};   
　　	execve("/bin/ls", argv, envp); 
	return 0;
}  
