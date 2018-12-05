#include <unistd.h>  
#include <stdio.h>  
#include <sys/types.h>  
#include <stdlib.h>  
#include <wait.h>
int main()  
{  
        pid_t pid;  
        pid = fork();  
        if(pid==0)  
        {  
                printf("error fork:%m\n");  
                exit(-1);  
        }  
        else  
        {  
		wait(NULL);
                execl("/bin/ls","ls","-l","/etc",(char *)0);  
        }   
        return 0;  
}  
