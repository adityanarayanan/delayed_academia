;;; package -- Load Flymake/Flycheck stuff
;;; Commentary:
;;; Code:

;; Permanently enable Flycheck
(add-hook 'after-init-hook #'global-flycheck-mode)

;; Set Flycheck to follow C++11 standard while checking c++ files
(add-hook 'c++-mode-hook (lambda () (setq flycheck-clang-language-standard "c++11")))

(provide 'init_make_lint)
;;; init_make_lint ends here
